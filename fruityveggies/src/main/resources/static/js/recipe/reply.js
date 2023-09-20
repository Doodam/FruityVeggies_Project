/**
 * recipe reply.js
 * 댓글 영역 보이기
 * 댓글 검색, 등록, 수정, 삭제
 */

document.addEventListener('DOMContentLoaded', ()=>{
    //로그인 사용자 이름 -> 댓글 등록, 수정, 삭제할 때 사용
    const authName = document.querySelector('div#authName').innerTEXT;
    
    //댓글 목록 불러오기
    getRepliesWithRecipeId();
});
    //댓글 삭제 버튼들이 클릭을 처리하는 이벤트 리스너 콜백:
    const deletReply = (e) => {
        const result = conirm('정말 삭제할까요?')
        if(!result){
            return;
        }
        
        //삭제할 댓글 아이디
        const id = e.target.getAttribute('data-id');
        
        //Ajax DELETE 방식 요청 주소
        const reqUrl = `/api/reply/${id}`;
        
        axios
            .delete(reqUrl) //ajax에 delete 요청보냄
            .then((response)=>{
                //댓글 목록 새로고침
                getRepliesWithRecipeId();
            }) //성공 응답일 때 실행할 콜백 등록
            .catch((error)=>console.log(error)); // 실패 응답일 때 실행할 콜백 등록
    }



 
 
 