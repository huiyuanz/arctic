<template>
  <div class="data-size-wrap g-mr-16">
    <div class="common-header">
      <span class="name">{{$t('dataSize')}}</span>
      <TimeSelect @timeChange="handleTimeChange" />
    </div>
    <LineChart :data="chartLineData" :dataInfo="dataInfo" :tipFormat="tipFormat" :loading="loading" />
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useI18n } from 'vue-i18n'
import LineChart from './LineChart.vue'
import TimeSelect from './TimeSelect.vue'
import { getDataSizeChartData } from '@/services/overview.service'
import { ITimeInfo, IChartLineData } from '@/types/common.type'

const { t } = useI18n()
const loading = ref<boolean>(false)
const chartLineData = reactive<IChartLineData>(
  {
    timeLine: [],
    data1: []
  }
)
const dataInfo = reactive<ITimeInfo>({
  yTitle: '',
  colors: ['#5E82C2'],
  name: [t('data')]
})

const startTime = ref<string>()
const endTime = ref<string>()

function handleTimeChange(val) {
  const { start, end } = val
  startTime.value = start
  endTime.value = end
  getLineChartData()
}

async function getLineChartData() {
  try {
    loading.value = true
    const res = await getDataSizeChartData({
      startTime: startTime.value,
      endTime: endTime.value
    })
    const { timeLine, size } = res
    chartLineData.timeLine = timeLine
    chartLineData.data1 = size
  } finally {
    loading.value = false
  }
}
function tipFormat(data) {
  let str = `<span style="font-size: 10px">${data[0].axisValue}</span><br/>`
  for (let i = 0; i < data.length; i++) {
    str += `<span style="display: inline-block;background-color:${data[i].color}; margin-right: 6px; width: 6px;height: 6px;"></span>${data[i].seriesName}: ${data[i].value} G`
  }
  return str
}
</script>
<style lang="less" scoped>
.data-size-wrap {
  padding: 0 24px 16px;
  display: flex;
  width: 50%;
  flex-direction: column;
  box-shadow: 0 1px 4px rgb(0 21 41 / 8%);
  .name {
    font-weight: 500;
    font-size: 16px;
    line-height: 24px;
    color: @header-color;
  }
}
</style>
