<template>
  <div id="program-info-container">
    <div id="header">Det var en gång en fågel</div>
    <div id="left-box">
      <div id="description" class="selectable">
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
        Sequi labore explicabo voluptate facere, dolores et.
        Perspiciatis libero repellat quos neque?
      </div>
      <div id="icon-bar">
        <div id="share" class="tmp-icon">
          Share
        </div>
        <div id="favourite" class="tmp-icon">
          Favourite
        </div>
      </div>
    </div>
    <div id="right-box">
      <img src="https://static-cdn.sr.se/images/114/b3c02693-d242-405f-920d-9607396bd8b8.jpg?preset=api-default-square">
    </div>
  </div>
</template>

<script>
export default {
  props: ['program']
}
</script>

<style scoped>
#program-info-container {
  display: grid;
  grid-template-columns: auto min-content;
  grid-template-rows: min-content auto;
  background-color: rgb(255, 192, 203);
  border-radius: 10px;
  padding: var(--medium-padding);
  grid-gap: var(--medium-padding);
}

#header {
  grid-row: 1 / 2;
  grid-column: 1 / 3;
  font-size: 2em;
  text-align: center;
  background-color: rgb(246, 178, 191);
  border-radius: inherit;
  padding: var(--small-padding);
}

#left-box {
  grid-row: 2 / 2;
  grid-column: 1 / 2;

  display: grid;
  grid-template-rows: auto min-content;
  background-color: rgb(246, 178, 191);
  padding: var(--medium-padding);
  border-radius: inherit;
}

#description {
  height: 100%;
}

#icon-bar {
  display: flex;
  flex-direction: row;
  align-content: center;
  justify-content: flex-end;
}

.tmp-icon {
  background-color: plum;
  margin-left: 5px;
  padding: 3px;
  border-radius: 5px;
  cursor: pointer;
  border: 1px solid rgb(182, 77, 182);
}

.tmp-icon:hover {
  background-color: rgb(218, 138, 218);
}

.tmp-icon:active {
  background-color: rgb(209, 108, 209);
}

#right-box {
  grid-row: 2 / 3;
  display: flex;
}

#right-box > img {
  max-width: 150px;
  border-radius: 8px;
  align-self: center;
}
</style>
