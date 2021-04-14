<template>
  <div id="program-group-container">
    <div id="scroll">
      <div v-for="(program, i) in programs" :key="i" :movie="movie" id="program-div"></div>
    </div>
  </div>
</template>

<!--

* props: programs[]

-->

<script>
export default {
  props: ['programs']
}
</script>

<style>

</style>
