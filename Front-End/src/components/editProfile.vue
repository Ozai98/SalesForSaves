<template>
  <div class="container">
    <div class="profile">
      <link
        href="https://fonts.googleapis.com/css2?family=Oswald:wght@700&display=swap"
        rel="stylesheet"
      />
      <h1 class="highText">Información de perfil</h1>
      <div class="profilePicFrame">
        <img src="@/assets/13440.jpg" alt="profile pic" />
      </div>
      <br />
      <label for="unField">Nombre</label>
      <input type="text" id="unField" class="highText" :value="UserName" />
      <label for="mailField">Correo</label>
      <input type="text" id="mailField" class="highText" />
      <button class="editBtn">EDITAR</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileView",
  props: {
    UserName: String,
    Mail: String
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

.profile {
  background-color: white;
  font-family: "Oswald", sans-serif;
  text-align: center;
  border-radius: 25px 25px 0 0;
  position: absolute;
  height: 95%;
  width: 60%;
  margin-left: 20%;
  bottom: 0;
}
.profilePicFrame {
  border-color: #ff8e43;
  border-width: 1px;
  border-style: solid;
  display: inline-block;
  width: 200px;
  height: 200px;
  overflow: hidden;
  border-radius: 50%;
}
.profilePicFrame img {
  width: auto;
  height: 100%;
  margin-left: -15%;
}

.highText {
  font-size: 53px;
}

.container {
  background-color: #a1ffca;
  position: absolute;
  height: 100%;
  width: 100%;
  bottom: 0;
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
}
</style>
