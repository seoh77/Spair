<template>
    <div id="detail-container">
        <div id="details">

            <h4>게시글 상세</h4>
            <div id="wrap">
                <div id="grid">
                    
                        <div class="item">제목</div> 
                        <div class="item">{{ store.board.title }}</div>
                    
                        <div class="item">작성자</div>
                        <div class="item">{{ store.user.nickname }}</div>
                    
                        <div class="item">모집상태</div>
                        <div class="item" :class="{ 'red': store.board.status }">{{ store.board.status ? '모집중' : '모집완료' }}</div>
                    
                        <div class="item">모집인원</div> 
                        <div class="item">{{ store.board.recruitmentNum }}</div>
                   
                        <div class="item">모집성별</div> 
                        <div class="item">{{ store.board.gender == 1 ? '남성' : '여성'  }}</div>
                  
                        <div class="item">가격</div>
                        <div class="item">{{ store.board.price  }}</div>
                    
                        <div class="item">주소 </div>
                        <div class="item">{{ store.sportsCenter.roadAddress }}</div>
                        
                        <div class="item">운동종류</div> 
                        <div class="item">{{ store.board.exerciseType }}</div>
                </div>
                
                <div id="con">
                    <div id="inner-wrap">
                        <div class="tem">내용 </div> 
                        <div class="tem">{{ store.board.modifiedDate }}</div>
                    </div> 
                    <div>{{ store.board.content }}</div>
                    <div id="btn" v-if="isWriter">
                        <button @click="deleteBoard">삭제</button>
                        <button @click="updateBoard">수정</button>
                    </div>
                </div>
            </div>
            
            <h4>댓글</h4>
            <CommentList/>
        </div>
    </div>
</template>

<script setup>
import { useBoardStore } from '@/stores/board';
import { useRoute, useRouter } from 'vue-router';
import { ref, onMounted } from 'vue'
import CommentList from '@/components/comment/CommentList.vue'
import axios from 'axios';

const store = useBoardStore()
const route = useRoute()
const router = useRouter()

const isWriter = ref(false)

onMounted(async()=> {
    const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo"))
    const data = await store.getBoard(route.params.postId)

    if(data.userId === localStorageData.userId) {
        isWriter.value = true
    }
})

// 게시글 삭제 
const deleteBoard = function(){
    const isDelete = confirm("정말 삭제하시겠습니까?")

    if(isDelete) {
        axios.delete(`http://localhost:8080/api/board/${route.params.postId}`)
        .then(() => {
            router.push({ name: 'boardList' })
        })
    } 
}

// 게시글 수정 
const updateBoard = function(){
   router.push({name: 'boardUpdate'})
}

</script>

<style scoped>
#detail-container {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

#details {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 2rem;
}

h4 {
    width: 100%;
    display: flex;
    justify-content: start;
    align-items: center;
    font-family: 'Tenada';
    font-size: 1.5rem;
    margin: 0 0 0.5rem 0;
}

#wrap {
    width: 100%;
    display: flex;
    flex-direction: column;
    font-size: 1.2rem;
    font-family: 'NanumSquareRound';
    margin: 1rem 0;
}

#wrap div {
    margin: 0.8rem 0.5rem ;
}

#grid {
    display: grid;
    column-gap: 0.7rem;
    grid-template-rows: 1fr 1fr 1fr 1fr;
    grid-template-columns: repeat(8, 1fr);
}

.red {
    color: red;
}

.item {
    display: flex;
    align-items: center;
}

.item:nth-child(14) {
    grid-column: 2 / span 7;
}

.item:nth-child(odd) {
    font-weight: bold;
    /* text-align: center; */
    background-color: var(--secondary-color);
    border-radius: 0.6rem;
    height: 2rem;
    display: flex;
    justify-content: center;
    align-items: center;
}

.item:nth-child(1) {
    background-color: var(--sub-color);
}

.item:nth-child(2) {
    grid-column: 2 / span 5;
}

.item:nth-child(3) {
    background-color: var(--sub-color);
}

#con {
    display: flex;
    flex-direction: column;
    align-items: center;
    border: 0.1rem solid var(--gray-color);
    box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
    padding-bottom: 30px ;
}

#con div {
    width: 90%;
    margin: 0.7rem 0;
}

#inner-wrap {
    display: grid;
    grid-template-columns: 1fr 6fr;
    grid-template-rows: 1fr;
}

.tem:nth-child(1) {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: var(--secondary-color);
    border-radius: 0.6rem;
    font-weight: bold;
    height: 2rem;
}

.tem:nth-child(2) {
    text-align: end;
    color: var(--gray-color);
    font-size: 1rem;
}

#btn {
    display: flex;
    flex-direction: row-reverse;
}

button {
    width: 10%;
    border-style: none;
    margin: 0 1rem;
    border-radius: 1rem;
    font-size: 1.3rem;
    font-weight: bold;
    font-family: 'NanumSquareRound';
    height: 2.5rem;
}

button:nth-child(1) {
    background-color: red;
}

button:nth-child(2) {
    background-color: var(--secondary-color);
}

</style>