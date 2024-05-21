
<template>
  <div id="container">
    <TheHeaderNav/>
    <main>
      <RouterView />
    </main>
    <TheFooter/>
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import TheHeaderNav from '@/components/common/TheHeaderNav.vue'
import TheFooter from '@/components/common/TheFooter.vue';
</script>

<style scoped>
  #container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }

  main {
    width: 100%;
    margin-bottom: 50px ;
    min-height: 800px;
  }
</style>
