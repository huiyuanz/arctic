<template>
  <div class="overview-wrap">
    <ResourceCard />
    <div class="module-card g-flex">
      <ResourceUsage />
      <OptimizingTables />
    </div>
    <div class="module-card g-flex">
      <DataSize />
      <TopTables />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import ResourceCard from './ResourceCard.vue'
import ResourceUsage from './ResourceUsage.vue'
import OptimizingTables from './OptimizingTable.vue'
import DataSize from './DataSize.vue'
import TopTables from './TopTables.vue'

onMounted(() => {
})

</script>

<style lang="less">
.overview-wrap {
  padding: 16px 24px 24px;
  .module-card {
    height: 414px;
    margin-top: 16px;
    flex: 1;
  }
  .common-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 16px 0;
    font-size: 16px;
    line-height: 24px;
    font-weight: 500;
    color: @header-color;
  }
}
</style>
