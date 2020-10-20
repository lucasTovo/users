<template>
  <div class="jumbotron">
    <div class="container">
      <div class="row">
        <div class="col-sm-8 offset-sm-2">
          <div>
            <h2>{{ $t('home.forgot') }}</h2>
            <form @submit.prevent="sendData">
              <div class="form-group">
                <label for="email">{{ $t('home.email') }}</label>
                <input
                  id="email"
                  v-model="user.email"
                  type="email"
                  name="email"
                  class="form-control"
                  :class="{ 'is-invalid': submitted && $v.user.email.$error }"
                />
                <div
                  v-if="submitted && $v.user.email.$error"
                  class="invalid-feedback"
                >
                  <span v-if="!$v.user.email.required">{{
                    $t('required.email')
                  }}</span>
                  <span v-if="!$v.user.email.email">{{
                    $t('required.email_invalid')
                  }}</span>
                </div>
              </div>
              <div class="form-group">
                <b-button
                  class="bbutton"
                  variant="outline-primary"
                  type="submit"
                  :disabled="$v.$anyError"
                  size="lg"
                >
                  {{ $t('home.submit') }}
                </b-button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { required, email, minLength, sameAs } from 'vuelidate/lib/validators'

export default {
  name: 'App',
  data() {
    return {
      user: {
        email: '',
      },
      submitted: false,
    }
  },
  validations: {
    user: {
      email: { required, email },
    },
  },
  methods: {
    sendData() {
      this.submitted = true
      // stop here if form is invalid
      this.$v.$touch()
      if (this.$v.$invalid) {
      }
      const u = new URLSearchParams({ ...this.user }).toString()
      this.$axios
        .post('forgotPass/?' + u)
        .then((response) => {
          // If request is good...
          this.$router.push({ name: 'fback-forgot' })
          console.log(response.data)
        })
        .catch((error) => {
          console.log('error ' + error)
        })
    },
  },
}
</script>

<style></style>
