
<template>
  <main>
      <Intro/>
      <Advertisement/>
      <div id="title">
        최신 PAIR 찾기글
      </div>
      <BoardList/>
  </main>
</template>

<script setup>
import Intro from '@/components/home/Intro.vue'  
import Advertisement from '@/components/home/Advertisement.vue';
import BoardList from '@/components/board/BoardList.vue'
</script>

<style scoped>
  main {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;  
  }
  #title {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display: flex;
    justify-content: start;
    align-items: center;
    font-family: 'Tenada';
    font-size: 2rem;
    margin: 2rem;
  }
  
</style>