$(document).ready(function() {
  var storedUsername = localStorage.getItem("username");
  var storedEmail = localStorage.getItem("em");
  var storedScore = localStorage.getItem("score");
  var storedAct = localStorage.getItem("act");
  if(storedAct = null)
  {
      storedAct = 1;
  }
  $('#userName').text('Username: ' + storedUsername);
  $('#userScore').text(storedScore + '%\nHappy');
  $('#med').text(storedAct);
});
