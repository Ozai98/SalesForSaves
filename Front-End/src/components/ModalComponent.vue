<template>
  <transition name="fade">
    <div class="modal" v-if="show">
      
      <div class="modal__backdrop" @click="closeModal()" />
          
      <div class="modal__dialog">
        <div class="modal__header">
          
          <button type="button" class="modal__close" @click="closeModal()">
            <img src="@/assets/imgs/close.svg" alt="" />
          </button>

          <slot name="header" />
        </div>

        <div class="modal__body">
          <slot name="body" />
        </div>

        <div class="modal__footer">
          <slot name="footer" />
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "Modal",
  data() {
    return {
      show: false,
      tittle:''
    };
    
  },
  methods: {
    closeModal() {
      this.show = false;
      document.querySelector("body").classList.remove("overflow-hidden");
    },
    openModal() {
      this.show = true;
      document.querySelector("body").classList.add("overflow-hidden");
    },
  },
  props: {
    bcolor: String,
  },
};
</script>

<style lang="scss" scoped>
.modal {
  overflow-x: hidden;
  overflow-y: hidden;
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 9;
  &__backdrop {
    background-color: #ff8e43;
    opacity: 0.8;
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 1;
  }
  &__dialog {
    background-color: #ffffff;
    position: relative;
    width: 600px;
    margin: 6vw auto;
    display: grid;
    //max-height: 560px;
    border-radius: 1vw;
    z-index: 2;
    @media screen and (max-width: 992px) {
      width: 90%;
    }
  }
  &__close {
    width: 2vw;
    height: 2vw;
    align-self: end;
    outline: none;
    border: none;
    justify-self: center;
    cursor: pointer;
    background-color: white;
    float:right;
  }
  &__header {
    grid-row: 1;
    justify-self: end;
    padding: 20px 20px 10px;
    width: 560px;
  }
  
  &__body {
    grid-row: 2;
    padding: 10px 20px 10px;
    overflow-y: scroll;
    overflow-x: hidden;
  }
  &__footer {
    padding: 10px 20px 20px;
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
