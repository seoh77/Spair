
<template>
  <main>
      <Intro/>
      <Advertisement/>
  </main>
</template>

<script setup>
import Intro from '@/components/home/Intro.vue'  
import Advertisement from '@/components/home/Advertisement.vue';
</script>

<style scoped>
  main {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    
  }
</style>