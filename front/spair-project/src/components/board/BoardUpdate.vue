<template>
    <div id="update-container">
        <h4>게시글 수정</h4>
        <!--수정 가능 항목: 제목, 상태, 성별, 인원, 가격, 내용-->
        <div id="form-container">
            <div id="update">
                <div>
                    <label id="nickname-label" for="nickname">작성자</label>
                    <input type="text" id="nickname" readonly v-model="store.user.nickname">
                </div>
                <div>
                    <label id="title-label" for="title">제목  </label>
                    <input type="text" id="title" v-model="store.board.title">
                </div>

                <!-- 모집 정보 -->
                <div id="infos">
                    <div>
                        <label for="status">모집상태  </label>
                        <select name="status" id="status" v-model="store.board.status">
                            <option value="1">모집중</option>
                            <option value="0">모집완료</option>
                        </select>
                    </div>
                    <div>
                        <label for="gender">모집성별  </label>
                        <select name="gender" id="gender" v-model="store.board.gender">
                            <option value="1">남성</option>
                            <option value="2">여성</option>
                            <option value="3">상관없음</option>
                        </select>
                    </div>
                    <div>
                        <label for="recruitment_num">모집인원  </label>
                        <select name="recruitment_num" id="recruitment_num" v-model="store.board.recruitmentNum">
                            <option value="1명">1명</option>
                            <option value="2명">2명</option>
                            <option value="3명 이상">3명 이상</option>
                            <option value="상관없음">상관없음</option>
                        </select>
                    </div>
                    <div>
                        <label for="exerciseType">운동종류</label>
                        <input type="text" id="exerciseType" readonly v-model="store.board.exerciseType">
                    </div>
                    <div id="price-wrap">
                        <label for="price">가격  </label>
                        <input type="price" id="price" placeholder="1인당 가격을 입력하세요." v-model="store.board.price">
                        <span>원</span>
                    </div>
                </div>

                    <div>
                        <label id="road-address" for="roadAddress">주소 </label>
                        <input type="text" id="roadAddress" readonly v-model="store.sportsCenter.roadAddress">
                    </div>
                    <div id="area">
                        <label for="content">내용  </label>
                        <textarea id="content" rows="10" v-model="store.board.content"></textarea>
                    </div>
                    <div id="up-btn">
                        <!-- API 연결 후 click이벤트 수정 예정-->
                        <button @click="updateBoard">수정</button>
                    </div>
                    
                </div>
            </div>   
        </div>
    </template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { useRoute, useRouter } from "vue-router";
import { onMounted } from 'vue'

const store = useBoardStore();
const route = useRoute()

const updateBoard = function () {
    store.updateBoard(route.params.postId)
}

onMounted(() => {
    const postId = route.params.postId;
    store.getBoard(postId);
})


</script>

<style scoped>
#update-container {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

h4 {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display:flex;
    justify-content: start;
    align-items: center;
    font-family: 'Tenada';
    font-size: 1.5rem;
    margin: 1rem;
}

#form-container {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    border: 0.1rem solid var(--gray-color);
    box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
}

#update {
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: start;
    font-family: 'NanumSquareRound';
    font-size: 1rem;
    margin: 3rem;
}

#update div {
    width: 100%; 
    display: flex;
    margin-bottom: 1rem;
}

#nickname-label {
    width: 8.5%;
}

#title-label {
    width: 8.7%;
}

#road-address {
    width: 8.5%;
}

#title {
    width: 91.3%;
}

#infos {
    display: flex;
    justify-content: space-between;
    align-items: end;
}

#infos div {
    width: 20%;
    margin-bottom: 0;
}

#infos label {
    margin: 0 0.5rem 0 0;
    width: 40%;
}

#exerciseType {
    width: 40%;
}

#price-wrap input{
    width: 90%;
    margin-right: 5px ;
}

label {
    width: 12%;
    font-size: 1.2rem;
    font-weight: bold;
}

textarea {
    width: 100%;
    resize: none;
    overflow-y: auto; 
    overflow-x: hidden;
    font-size: 1.2rem;
    margin: 1rem 0;
    padding: 5px ;
    font-family: 'NanumSquareRound';
}

#area {
    display: flex;
    flex-direction: column;
}

input {
    width: 88%;
    border-style: none;
    border-bottom: 0.1rem solid #000000;
    outline: none;
}

#up-btn {
        display: flex;
        flex-direction: row-reverse;
}

button {
    border-style: none;
    background-color: var(--secondary-color);
    font-size: 1.2rem;
    border-radius: 0.5rem;
    width: 7rem;
    height: 2.5rem;
    font-weight: bold;
}

input:read-only {
    border: none;
}
</style>