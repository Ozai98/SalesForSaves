function timeSince(date) {
  var seconds = Math.floor((new Date() - date) / 1000);

  var interval = Math.floor(seconds / 31536000);

  if (interval > 1) {
    return "Hace " + interval + " años";
  }
  interval = Math.floor(seconds / 2592000);
  if (interval > 1) {
    return "Hace " + interval + " meses";
  }
  interval = Math.floor(seconds / 86400);
  if (interval > 1) {
    return "Hace " + interval + " días";
  }
  interval = Math.floor(seconds / 3600);
  if (interval > 1) {
    return "Hace " + interval + " horas";
  }
  interval = Math.floor(seconds / 60);
  if (interval > 1) {
    return "Hace " + interval + " minutos";
  }
  return "Hace " + Math.floor(seconds) + " segundos";
}
function timeTil(date) {
  var options = {
    weekday: "long",
    year: "numeric",
    month: "long",
    day: "numeric",
  };
  var tempDate = new Date(date);
  console.log(tempDate);
  var outS = tempDate.toLocaleDateString("es-ES", options);
  return "Disponible hasta el " + outS;
}

export { timeSince, timeTil };
