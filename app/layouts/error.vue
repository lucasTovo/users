<template>
  <div class="container">
    <h1 v-if="error.statusCode === 404">{{ $t('home.error_page') }}</h1>
    <h1 v-else>An error occurred</h1>
    <Button>
      <NuxtLink to="/">{{ $t('links.home') }}</NuxtLink>
    </Button>
    <LocaleChanger></LocaleChanger>
  </div>
</template>

<script>
export default {
  props: ['error'],
  layout: 'blog', // you can set a custom layout for the error page
}
</script>
