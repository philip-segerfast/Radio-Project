<template>
  <div class="info-header-container">
    <div class="header"> {{ program.name }} </div>
    <div class="left-box">
      <div class="description selectable">
        {{ program.description }}
      </div>
      <div class="icon-bar">
        <div class="share tmp-icon">
          Share
        </div>
        <div class="favourite tmp-icon">
          Favourite
        </div>
      </div>
    </div>
    <div class="right-box">
      <img :src='program.programImage'>
    </div>
  </div>
</template>

<script>
export default {
  props: ['program']
}
</script>
