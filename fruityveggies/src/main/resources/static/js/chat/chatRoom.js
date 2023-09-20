
$(document).ready(function () {
  const roomName = document.querySelector('span#chatroomName').innerText;
  const roomId = document.querySelector('span#chatroomRoomId').innerText;
  const username = document.querySelector('span#chatroomUsername').innerText;
  
  console.log("채팅방개설됨" + roomName + ", " + roomId + ", " + username);

  var sockJs = new SockJS("/stomp/chat");
  // SockJS를 내부에 들고있는 stomp를 내어줌
  var stomp = Stomp.over(sockJs);

  // connection이 맺어지면 실행
  stomp.connect({}, function () {
    console.log("STOMP Connection");

    // subscribe(path, callback)으로 메세지를 받을 수 있음
    stomp.subscribe("/sub/chat/room/" + roomId, function (chat) {
      // JSON -> js 객체로 변환
      var content = JSON.parse(chat.body);

      console.log("콜백" + roomName + ":" + content);
		console.log('Stomp Version:', Stomp.VERSIONS);
		console.log('jQuery version:', jQuery.fn.jquery);
		console.log('Moment.js version:', moment.version);

		
      var writer = content.writer;
      var str = "";

      // 내 메세지
      if (writer === username) {
        str = "<div class='message clearfix'>";
        str += "<div class='message-data align-right'>";
        str += "<span class='message-data-name'>You</span> <i class='fa fa-circle me'></i>";
        str += "</div>";
        str += "<div class='message my-message float-right'>";
        str += content.message;
        str += "</div>";
        str += "</div>";
      } else {
        // 다른사람 메세지
        str = "<div class='message clearfix'>";
        str += "<div class='message-data align-left'>";
        str +=
          "<span class='message-data-name'><i class='fa fa-circle online'></i> " +
          writer +
          "</span>";
        str += "</div>";
        str += "<div class='message other-message align-left'>";
        str += content.message;
        str += "</div>";
        str += "</div>";
      }

      $("#msgArea").append(str);

      // 스크롤을 항상 아래로 이동
      var chatHistory = document.getElementById("msgArea");
      chatHistory.scrollTop = chatHistory.scrollHeight;
    });

    // 입장시 메세지 발송 send(path, header, message)
    // js객체 -> JSON 형식으로 변환
    stomp.send("/pub/chat/join", {}, JSON.stringify({ roomId: roomId, writer: username }));
  });

  // 전송 버튼 클릭시 /pub/chat/message 로 메세지 발송
  $("#button-send").on("click", function () {
    var message = $("#message-to-send").val();
    if (message) {
      stomp.send(
        "/pub/chat/message",
        {},
        JSON.stringify({ roomId: roomId, message: message, writer: username })
      );
      console.log("Message Sent: " + username + ": " + message);
      $("#message-to-send").val("");
    }
  });

  // 퇴장 버튼 클릭시 /pub/chat/exit 로 메세지 발송
  $("#button-exit").on("click", function () {
    var message = $("#message-to-send").val();
    stomp.send("/pub/chat/exit", {}, JSON.stringify({ roomId: roomId, writer: username }));
    console.log("Exit Message: " + username + ": " + message);
    window.location.href = "http://localhost:8030/";
  });
});
