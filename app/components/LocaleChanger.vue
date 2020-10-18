<template>
  <div class="locale-changer">
    <select v-model="$i18n.locale" class="change">
      <option
        v-for="(lang, i) in langs"
        :key="`Lang${i}`"
        :value="lang"
        class="change"
      >
        {{ lang }}
      </option>
    </select>
    <div class="style-name">
      {{ $t('home.changer') }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'LocaleChanger',
  data() {
    return { langs: ['fr', 'en', 'pt'] }
  },
}
</script>

<style scoped>
.change {
  display: inline-block;
  margin: 0.2em 0;
  padding: 0.2em 0.5em;
  background: #fff;
  border: 2px solid orange;
  border-radius: 3px;
  color: orange;
  font-family: 'Quicksand', sans-serif;
  font-size: 1em;
  font-weight: 700;
  letter-spacing: 0.02em;
  line-height: 1;
  text-decoration: none;
  text-transform: uppercase;
  cursor: pointer;
  transition: 0.3s;
  position: fixed;
  left: 2em;
  bottom: 2em;
}

.change:hover {
  background: black;
  color: orange;
}

.style-name {
  position: fixed;
  left: 2em;
  bottom: 4em;
  font-family: 'Quicksand', sans-serif;
  font-size: 1rem;
}
</style>
