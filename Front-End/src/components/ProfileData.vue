<template>
  <div id="container">
    <link
      href="https://fonts.googleapis.com/css2?family=Oswald:wght@700&display=swap"
      rel="stylesheet"
    />
    <div id="profile">
      <h1 class="highText">INFORMACIÓN DE PERFIL</h1>
      <div class="profilePicFrame">
        <img src="@/assets/13440.jpg" alt="profile pic" />
      </div>
      <br />
      <label for="unField">Nombre</label>
      <p id="unField" class="highText">{{ UserName }}</p>
      <label for="mailField">Correo</label>
      <p id="mailField" class="highText">{{ Mail }}</p>
      <button class="editBtn">EDITAR</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileView",
  props: {
    UserName: String,
    Mail: String,
    Alt: Boolean
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
* {
  margin: 0;
  padding: 0;
}

label {
  font-size: 14px;
  font-family: Verdana, sans-serif;
  color: #888888;
}

#profile {
  background-color: white;
  font-family: "Oswald", sans-serif;
  text-align: center;
  border-radius: 25px 25px 25px 25px;
  position: absolute;
  height: 80%;
  width: 60%;
  margin: 50px 0 0 20%;
  margin-bottom: 50px;
}
.profilePicFrame {
  border-color: #ff8e43;
  border-width: 1px;
  border-style: solid;
  display: inline-block;
  width: 270px;
  height: 270px;
  overflow: hidden;
  border-radius: 50%;
  margin: 50px 0 20px 0;
}
.profilePicFrame img {
  width: auto;
  height: 100%;
  margin-left: -15%;
}

.highText {
  font-size: 53px;
}

#container {
  background-color: #a1ffca;
  position: absolute;
  top: 0%;
  left: 0%;
  height: 100%;
  width: 100%;
  overflow: auto;
}
.editBtn {
  min-width: 120px;
  min-height: 40px;
  font-size: 26px;
  color: #ff8e43;
  border-style: solid;
  border-width: 1px;
  border-color: #ff8e43;
  font-family: "Oswald", sans-serif;
  margin: 20px 0 10px 0;
  border-radius: 16px;
}
</style>
