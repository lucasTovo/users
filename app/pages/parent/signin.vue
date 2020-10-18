<template>
  <div class="jumbotron">
    <div class="container">
      <div class="row">
        <div class="col-sm-8 offset-sm-2">
          <div>
            <h2>{{ $t('home.signin') }}</h2>
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
                <label for="password">{{ $t('home.password') }}</label>
                <input
                  id="password"
                  v-model="user.password"
                  type="password"
                  name="password"
                  class="form-control"
                  :class="{
                    'is-invalid': submitted && $v.user.password.$error,
                  }"
                />
                <div
                  v-if="submitted && $v.user.password.$error"
                  class="invalid-feedback"
                >
                  <span v-if="!$v.user.password.required">{{
                    $t('required.password')
                  }}</span>
                  <span v-if="!$v.user.password.minLength">{{
                    $t('required.pass_char')
                  }}</span>
                </div>
              </div>
              <div class="form-group">
                <b-button
                  class="bbutton"
                  variant="warning"
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
        password: '',
      },
      submitted: false,
    }
  },
  validations: {
    user: {
      email: { required, email },
      password: { required, minLength: minLength(6) },
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
        .post('login/?' + u)
        .then((response) => {
          // If request is good...
          this.$router.push({ name: 'fback-signin' })
          console.log(response.data)
        })
        .catch((error) => {
          console.log('error ' + error)
        })
    },
  },
}
</script>

<style>
.jumbotron {
  background: rgba(255, 149, 10, 0.5);
}
.bbutton:hover {
  background: black;
  color: orange;
}
</style>
