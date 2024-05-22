<template>
    <div id="intro-container">

        <div id="wrap">
            <div id="h1">
                <h1>나랑 같이 운동하자!</h1>
                <div>
                    <h1 id="typing">내 <span>PAIR</span> 찾기</h1>
                </div>
            </div>
            <div id="shortcut">
                <!--  바로가기 컴포넌트 추가 후 클릭 이벤트 수정 및 추가 예정-->
                <button @click="gym">
                    우리동네 헬스장 PAIR찾기 바로가기
                </button>
                <button @click="pilates">
                    우리동네 필라테스 PAIR찾기 바로가기
                </button>
            </div>
            
        </div>

    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { onMounted, onUnmounted } from 'vue';
const router = useRouter()

// 우리동네 헬스장/필라테스 바로가기 기능
const gym = function(){
    // 쿼리를 담아서 boardList로 
    router.push({ name: 'boardList', query: { exerciseType: 'PT'}})
}
const pilates = function(){
    // 쿼리를 담아서 boardList로 
    router.push({ name: 'boardList', query: { exerciseType: '필라테스'}})
}


// 타이핑 애니메이션 기능
const typingDuration = 4000 // 타이핑 애니메이션 지속 시간 (ms)
const intervalDuration = 3000 // 애니메이션 주기 (ms)
let interval = null

const restart = () => {
  const typingElement = document.getElementById('typing')
  if (typingElement) {
    typingElement.style.animation = 'none'
    typingElement.offsetHeight
    typingElement.style.animation = null
  }
}

onMounted(() => {
  interval = setInterval(restart, typingDuration + intervalDuration)
})

onUnmounted(() => {
  if (interval) {
    clearInterval(interval);
  }
})


</script>

<style scoped>
    #intro-container {
        width: 100%; 
        background-color: var(--primary-color);
        display:flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        font-family: 'Tenada';
    }

    #wrap {
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
        padding: 4rem 1rem 0rem 1rem;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        align-items: center;
        /* background-color: #FF8B37;   */
        background-color: #3d2c2c;
         /* opacity: 80%; */
        border-radius: 5rem 5rem 0 0 ;
        /* box-shadow: 0.1rem 0.1rem 0 0.3rem #552808; */
        box-shadow: 0 -0.8rem 0.8rem -0.8rem #882e01,
              -0.8rem 0 0.8rem -0.8rem #882e01, 
              0.8rem 0 0.8rem -0.8rem #882e01;
    }

    #h1 {
        width: 75%;
        max-width: 1200px;
        min-width: 950px;
        display: flex;
        flex-direction: column;
        height: 8rem;
    }
    
    h1 {
        margin: 0;
        color: #FFFFFF;
        font-size: 3rem;
        height: 4rem;
    }

    #h1 div {
        display: flex;
        width: 17rem;
    }

    #typing {
        overflow: hidden;
        border-right: 0.3rem solid #FFFFFF;
        width: 0;
        height: 2.8rem;
        white-space: nowrap;
        animation: typing 1.3s steps(20, end) forwards, blink 0.8s infinite;
    }

    @keyframes typing {
        from { width: 0 }
        to { width: 100% } 
    }

    @keyframes blink {
        50% { border-color: transparent; }
    }

    span {
        color: var(--secondary-color);
    }

    #shortcut {
        width: 75%;
        max-width: 1200px;
        min-width: 950px;
        display: flex;
        justify-content: center;
    }

    button {
        border-style: none;
        border-radius: 10px;
        width: 400px;
        height: 130px;
        display: flex;
        align-items: flex-end;
        margin: 40px 10px;
        font-family: 'Tenada';
        padding: 1rem 1rem 0.5rem 1rem;
        cursor: pointer;
        box-shadow: 0.1rem 0.2rem 0.5rem #c55707
    }

    button:first-child {
        background-color: var(--sub-color);
        color:#FFFFFF;
        font-size: 1.4rem;
    }

    button:nth-child(2) {
        background-color: #FFFFFF;
        color: var(--sub-color);
        font-size: 1.4rem;
    }

    button:active {
        box-shadow: 0.1rem 0.1rem 0.1rem #aa4a06
    }

</style>