<template>
  <div>
    <button @click="exit">Return to homepage</button>
    <input id="hs" name="hash" type="hash" hidden />
    <span id="message"></span>
  </div>
</template>

<script>
export default {
  mounted() {
    const http = new XMLHttpRequest()
    const urlnow = window.location.href
    const url =
      'http://localhost:9080/orion-users-service/users/api/v1/confirmHash/?hash='

    const par = new URL(urlnow).searchParams
    const hash = par.get('hash')

    const params = 'hash=' + hash
    http.open('POST', url, true)

    // Send the proper header information along with the request
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')

    http.onreadystatechange = function () {
      // Call a function when the state changes.
      if (http.readyState === 4 && http.status === 200) {
        alert(http.responseText)
      }
    }
    http.send(params)
  },
  methods: {
    exit() {
      this.$router.push({ name: 'index' })
    },
  },
}
</script>
