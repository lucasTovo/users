<template>
  <div>
    <h1>{{ $t('home.account') }}</h1>
    <nav>
      <ul>
        <li>
          <NuxtLink to="/parent/signup">{{ $t('home.signup') }}</NuxtLink>
        </li>
        <li>
          <NuxtLink to="/parent/signin">{{ $t('home.signin') }}</NuxtLink>
        </li>
        <li>
          <NuxtLink to="/parent/forgot">{{ $t('home.forgot') }}</NuxtLink>
        </li>
      </ul>
    </nav>
    <NuxtChild />
  </div>
</template>
<style scoped>
ul {
  list-style: none;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0;
}
li {
  margin: 0 0.5rem;
  padding: 0.25rem;
  font-size: 1rem;
}
</style>
