<template>
  <div>
    <a v-if="href" :href="href" class="button">
      <slot />
    </a>
    <button v-else class="button">
      <slot />
    </button>
  </div>
</template>

<script>
export default {
  props: {
    href: {
      type: String,
      default: null,
    },
  },
}
</script>

<style scoped>
.button {
  display: inline-block;
  margin: 0.5em 0;
  padding: 1em 2em;
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
}

.button:hover {
  background: black;
  color: orange;
}
</style>
