<template>
  <header>
    <TheNavigation />
  </header>
</template>

<style scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 1rem;
  background-color: rgb(255, 250, 250);
  transition: 0.3s;
  font-family: 'Quicksand', sans-serif;
}

header:hover {
  background: black;
  color: rgb(255, 250, 250);
}
</style>
