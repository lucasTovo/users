<template>
  <div>
    <h1></h1>
    <Button>
      <NuxtLink to="/parent/signup">{{ $t('home.signup') }}</NuxtLink>
    </Button>
    <Button>
      <NuxtLink to="/parent/signin">{{ $t('home.signin') }}</NuxtLink>
    </Button>
    <Button>
      <NuxtLink to="/parent/forgot">{{ $t('home.forgot') }}</NuxtLink>
    </Button>
    <LocaleChanger></LocaleChanger>
  </div>
</template>
