  var btns = document.getElementsByClassName("detail-btn");

  for (let index = 0; index < btns.length; index++) {
      const btn = btns.item(index);
      btn.addEventListener("click", (evt) => {
          removeIds(document.getElementsByClassName("detail-btn"));
          btn.id = "selected";
      })
  }


  var removeIds = (btns) => {
      for (const b of btns) {
          b.id = undefined;
      }
      return btns;
  }