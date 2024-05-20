<template>
    <div id="create-container">
        <h4>게시글 등록</h4>
        <div id="form-container">
            <div id="register">
                <div>
                    <label for="title">제목  </label>
                    <input type="text" id="title" v-model="board.title">
                </div>

                <div>
                    <label for="nickname">작성자 </label>
                    <input type="text" id="nickname" :value="userNickname" readonly>
                </div>
                
                <!-- 모집 정보 -->
                <div>
                    <label for="status">모집상태  </label>
                    <select name="status" id="status" v-model="board.status">
                        <option value="1" >모집중</option>
                        <option value="0">모집완료</option>
                    </select>
                </div>
                <div>
                    <label for="gender">모집성별  </label>
                    <select name="gender" id="gender" v-model="board.gender">
                        <option value="" disabled>모집성별</option>
                        <option value="1">남성</option>
                        <option value="2">여성</option>
                        <option value="3">상관없음</option>
                    </select>
                </div>
                <div>
                    <label for="recruitment_num">모집인원  </label>
                    <select name="recruitment_num" id="recruitment_num" v-model="board.recruitmentNum">
                        <option value="" disabled>모집인원</option>
                        <option value="1명">1명</option>
                        <option value="2명">2명</option>
                        <option value="3명 이상">3명 이상</option>
                        <option value="상관없음">상관없음</option>
                    </select>
                </div>
                <div>
                    <label for="exercise_type">운동종류  </label>
                    <select name="exercise_type" id="exercise_type" v-model="board.exerciseType">
                        <option value="" disabled>운동종류</option>
                        <option value="PT">PT</option>
                        <option value="필라테스">필라테스</option>
                        <option value="기타">기타</option>
                    </select>
                </div>
                <div id="price-wrap">
                    <label for="price">가격</label>
                    <input type="price" id="price" placeholder="1인당 가격을 입력하세요" v-model="board.price">
                    <span>원</span>
                </div>

                <div id="address">
                    <!--도로명 주소 API 사용 예정 -->
                    <label for="road_address">스포츠시설 주소</label>
                    <div class="search_wrap">
                        <input type="road_address" id="road_address" placeholder="주소">
                        <div class="address_search_btn">주소찾기</div>
                    </div>
                    <input type="text" name="detail_address" id="detail_address" placeholder="상세주소">
                </div>
                
                <div id="area">
                    <label for="content">내용</label>
                    <textarea id="content" rows="10" v-model="board.content"></textarea>
                </div>
                <div id="regi-btn">
                    <button @click="boardCreate">등록</button>
                </div>
                </div>
            </div>
        </div>
    </template>

<script setup>
    import { useBoardStore } from '@/stores/board'
    import { onMounted, ref } from 'vue'

    const store = useBoardStore()
    const userNickname = ref()

    onMounted(() => {
        const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo")) 
        userNickname.value = localStorageData.nickname
    })

    const board = ref({
        title: '',
        nickname: userNickname,
        status: 1,
        gender: '',
        recruitmentNum: '',
        exerciseType: '',
        price: '',
        content: '',
    })

    const boardCreate = function(){
        store.createBoard(board.value)
    }
</script>

<style scoped>
    #create-container {
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

    #register {
        display: flex;
        flex-direction: column;
        justify-content: start;
        align-items: start;
        font-family: 'NanumSquareRound';
        font-size: 1rem;
        margin: 3rem;
    }

    #register > div {
        margin-bottom: 0.3rem;
        width: 100%; 
        display: flex;
        margin-bottom: 0.6rem;
        margin-bottom: 1rem;
    }

    #price-wrap input{
        width: 20%;
        margin-right: 5px
    }
    
    label {
        width: 12%;
        font-size: 1.2rem;
        font-weight: bold;
    }

    textarea {
        width: 100%;
        resize: none;
        overflow-y: scroll; 
        overflow-x: hidden;
        font-size: 1.2rem;
    }

    #area {
        display: flex;
        flex-direction: column;
    }

    input {
        width: 88%;
        outline: none;
        border-style: none;
        border-bottom: 0.1rem solid #000000;
    }

    input[id="nickname"] {
        border: none;
    }

    #address {
        display: flex;
        flex-direction: column;
    }

    #address label {
        width: 30%;
    }

    #regi-btn {
        display: flex;
        flex-direction: row-reverse;
    }

    .search_wrap {
        display: flex ;
        margin-top: 5px
    }

    .search_wrap input {
        margin-right: 10px ;
        height: 30px;
    }

    .address_search_btn {
        width: 10%;
        height: 30px;
        display: flex ;
        justify-content: center;
        align-items: center;
        border-radius: 10px;
        font-weight: 600 ;
        font-size: 0.9rem ;
        background-color: var(--primary-color);
        font-family: 'NanumSquareRound';
    }

    #detail_address {
        margin-top: 10px ;
        padding-bottom: 10px;
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
</style>