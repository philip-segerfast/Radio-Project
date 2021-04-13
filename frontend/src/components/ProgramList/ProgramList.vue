<template>
  <div id="program-list-container">
    <div id="program-list">
      <div id="program-item" class="shadow-2" v-for="program in programs" :key="program.id" v-on:click="programClicked(program)">
        {{ program.name }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['programs'],
  name: 'ProgramList',
  methods: {
    programClicked (program) {
      this.$router.push('/program/' + program.id)
    }
  }
}
</script>

<style scoped>
#program-list-container {
  height: 100%;
}

#program-list {
  display: flex;
  flex-wrap: wrap;
  padding: var(--medium-padding);
}

#program-item {
  width: fit-content;
  margin-bottom: var(--small-padding);
  background-color: #5aa188;
  border-radius: var(--small-border-radius);
  padding: 5px;
  transition: margin-left 0.1s, background-color 0.1s;
  margin: 5px;
  border: 1px solid black;
}

#program-item:hover {
  background-color: var(--background-brightest-hover);
  cursor: pointer;
}

#program-item:active {
  background-color: var(--background-brightest-active);
  cursor: pointer;
}
</style>
