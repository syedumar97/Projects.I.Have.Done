$(document).ready(function() {
  $('button#actbut').on('click', '.format#actbutton', function(event) {
    event.preventDefault();
    /* Act on the event */
    if (localStorage.act) {
          localStorage.act = Number(localStorage.act)+1;
        } else {
            localStorage.act = 1;
        }
  });
});
