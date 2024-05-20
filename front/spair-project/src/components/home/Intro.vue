<template>
    <div id="intro-container">
        <div id="h1">
            <h1>나랑 같이 운동하자!</h1>
            <h1>내 <span>PAIR</span> 찾기</h1>
        </div>
        <div id="shortcut">
            <!--  바로가기 컴포넌트 추가 후 클릭 이벤트 수정 및 추가 예정-->
            <button @click="gym">
                <p>
                    우리동네 헬스장 PAIR찾기 바로가기
                </p>
            </button>
            <button @click="pilates">
                <p>
                    우리동네 필라테스 PAIR찾기 바로가기
                </p>
            </button>
        </div>
        
    </div>
</template>

<script setup>
// 테스트를 위한 임시변수
import { useBoardStore } from '@/stores/board';
import { useRouter } from 'vue-router';
const router = useRouter()
const store = useBoardStore()
const gym = function(){
    console.log("헬스장 바로가기")
    router.push({ name: 'boardList'})
}
const pilates = function(){
    console.log("필라테스 바로가기")
    router.push({ name: 'boardList'})
}
</script>

<style scoped>
    #intro-container {
        width: 100%; 
        /* height: 200px;  */
        background-color: var(--primary-color);
        display:flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        font-family: 'Tenada';
    }
    #h1 {
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
        display: flex;
        flex-direction: column;
    }
    h1 {
        margin: 0;
        color: #FFFFFF;
        font-size: 3rem;
    }
    span {
        color: var(--secondary-color);
    }
    #shortcut {
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
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
    }
    button p {
        margin: 0.7rem;
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
</style>