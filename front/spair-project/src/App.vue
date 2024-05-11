
<template>
  <div id="container">
    <TheHeaderNav/>
    <RouterView />
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import TheHeaderNav from '@/components/common/TheHeaderNav.vue'
</script>

<style scoped>
  #container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }
</style>
