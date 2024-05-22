import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useCommentStore = defineStore('comment', () => {
  
  const commentList = ref([])
  
  const insertComment = async(postId) => {
    const response = await axios.get(`http://localhost:8080/api/comment/${postId}`)

    commentList.value = []
    const allComment = response.data 
    let commentIdx = -1
    let replyIdx

    allComment.forEach((comment) => {
        comment.isEditing = false
        // 부모 댓글인 경우 commentList에 추가
        if(!comment.parentId) {
            commentList.value.push(comment)
            commentList.value[++commentIdx].replyComment = {}
            replyIdx = 0
        } else {
            // 답글인 경우 부모댓글의 replyComment 요소에 추가
            commentList.value[commentIdx].replyComment[replyIdx++] = comment
        }
    })
  }

  return {
    commentList, insertComment
  }

})