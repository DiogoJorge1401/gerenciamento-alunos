function generate() {
  let txt = "ACA";
  let random = Math.floor(Math.random() * 10000)
  document.getElementById('matricula').value = (txt + random);
}