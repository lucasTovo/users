<template>
  <div class="jumbotron">
    <div class="container">
      <div class="row">
        <div class="col-sm-8 offset-sm-2">
          <div>
            <h2>{{ $t('home.signup') }}</h2>
            <form @submit.prevent="sendData">
              <div class="form-group">
                <label for="name">{{ $t('home.name') }}</label>
                <input
                  id="name"
                  v-model="user.name"
                  type="text"
                  name="name"
                  class="form-control"
                  :class="{
                    'is-invalid': submitted && $v.user.name.$error,
                  }"
                />
                <div
                  v-if="submitted && !$v.user.name.required"
                  class="invalid-feedback"
                >
                  {{ $t('required.name') }}
                </div>
              </div>

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
                <label for="confirmPassword">{{
                  $t('home.confirm_password')
                }}</label>
                <input
                  id="confirmPassword"
                  v-model="user.confirmPassword"
                  type="password"
                  name="confirmPassword"
                  class="form-control"
                  :class="{
                    'is-invalid': submitted && $v.user.confirmPassword.$error,
                  }"
                />
                <div
                  v-if="submitted && $v.user.confirmPassword.$error"
                  class="invalid-feedback"
                >
                  <span v-if="!$v.user.confirmPassword.required">{{
                    $t('required.confirm_password')
                  }}</span>
                  <span v-else-if="!$v.user.confirmPassword.sameAsPassword">{{
                    $t('required.pass_match')
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
        name: '',
        email: '',
        password: '',
      },
      submitted: false,
    }
  },
  validations: {
    user: {
      name: { required },
      email: { required, email },
      password: { required, minLength: minLength(6) },
      confirmPassword: { required, sameAsPassword: sameAs('password') },
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
        .post('create/?' + u)
        .then((response) => {
          // If request is good...
          this.$router.push({ name: 'fback-signup' })
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
@media (min-width: 416px) {
  .jumbotron {
    background: rgb(255, 250, 250);
    display: inline-block;
    width: 500px;
    margin: 0 auto;
  }
}
/*screen upto 400px*/
@media (max-width: 415px) {
  .jumbotron {
    background: rgb(255, 250, 250);
    display: inline-block;
    width: 300px;
    margin: 0 auto;
  }
}

.bbutton:hover {
  background: black;
  color: rgb(255, 250, 250);
  variant: 'outline-primary';
}
</style>
