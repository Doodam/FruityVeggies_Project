/**
 * 개설하기 누르면 채팅방 입장
 */

document.querySelector(".btn-create").addEventListener("click", (e) => {
  e.preventDefault();

  if (document.querySelector(".roomName").value === "") {
    alert("방 이름을 입력해 주세요.");
  } else {
    document.querySelector(".roomForm").submit();
  }
});
