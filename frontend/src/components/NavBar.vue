<script></script>

<template>
  <q-toolbar id="toolbar">
    <div class="row full-width">
      <div class="col text-left q-ma-auto">
        <q-btn
          flat
          @click="$emit('showhidedrawer')"
          round
          dense
          icon="menu"
          size="25px"
        />
      </div>

      <div class="col text-center q-ma-auto">
        <q-btn flat dense :to="{ name: 'home' }" @click="link = 'home'">
          <img src="@/assets/logo.svg" height="50" />
        </q-btn>
      </div>

      <div class="col row justify-end">
        <q-btn
          flat
          round
          dense
          icon="person"
          size="25px"
          :to="{ name: 'user' }"
          style="font-size: 20px"
        />
      </div>
    </div>
  </q-toolbar>
</template>

<style scoped lang="sass">

#toolbar
  left: 0px
  top: 0px
  display: flex
  flex-direction: row
  justify-content: space-between
  align-items: center
  padding: 0px 30px
  background: $primary
</style>
