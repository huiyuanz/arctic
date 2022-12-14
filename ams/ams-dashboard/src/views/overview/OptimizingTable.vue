<template>
  <div class="optimizing-list-wrap">
    <div class="common-header">
      <span class="name">{{`${$t('optimizing')} ${$t('tables')}`}}</span>
    </div>
    <a-table
      :columns="optimizerColumns"
      :data-source="optimizersList"
      :pagination="pagination"
      :loading="loading"
      :scroll="{ y: scrollY }"
      rowKey="tableName"
      >
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'tableNameOnly'">
          <span class="primary-link" :title="record.tableName" @click="goTableDetail(record)">
            {{ record.tableNameOnly }}
          </span>
        </template>
        <template v-if="column.dataIndex === 'optimizeStatus'">
          <span :style="{'background-color': (OPTIMIZING_STATUS_CONFIG[record.optimizeStatus] || {}).color}" class="status-icon"></span>
          <span>{{ record.optimizeStatus }}</span>
        </template>
      </template>
    </a-table>
  </div>
</template>
<script lang="ts" setup>
import { computed, onMounted, reactive, ref, shallowReactive } from 'vue'
import { IOptimizeTableItem, OPTIMIZING_STATUS_CONFIG } from '@/types/common.type'
import { getOptimizerTableList } from '@/services/optimize.service'
import { useI18n } from 'vue-i18n'
import { formatMS2DisplayTime } from '@/utils'
import { useRouter } from 'vue-router'

const { t } = useI18n()
const router = useRouter()

const scrollY = ref<number>(268)
const loading = ref<boolean>(false)
const page = ref<number>(1)
const pageSize = ref<number>(25)
const total = ref<number>(0)
const optimizerColumns = shallowReactive([
  { dataIndex: 'tableNameOnly', title: t('table'), ellipsis: true, scopedSlots: { customRender: 'tableNameOnly' } },
  { dataIndex: 'optimizeStatus', title: t('status'), ellipsis: true, width: '30%' },
  { dataIndex: 'durationDisplay', title: t('duration'), ellipsis: true, width: '20%' }
])
const optimizersList = reactive<IOptimizeTableItem[]>([])
const pagination = computed(() => {
  return {
    current: page.value,
    pageSize: pageSize.value,
    size: 'small',
    simple: true,
    total: total.value,
    onChange: current => {
      page.value = current
      getOptimizersList()
    }
  }
})

async function getOptimizersList () {
  try {
    optimizersList.length = 0
    loading.value = true
    const params = {
      optimizerGroup: 'all',
      page: page.value,
      pageSize: pageSize.value
    }
    const result = await getOptimizerTableList(params)
    const { list, total: totalNum } = result
    total.value = totalNum;
    (list || []).forEach((p: IOptimizeTableItem) => {
      p.tableNameOnly = (p.tableIdentifier.tableName || '')
      p.durationDisplay = formatMS2DisplayTime(p.duration || 0)
      optimizersList.push(p)
    })
  } catch (error) {
  } finally {
    loading.value = false
  }
}

function goTableDetail (record: IOptimizeTableItem) {
  const { catalog, database, tableName } = record.tableIdentifier
  router.push({
    path: '/tables',
    query: {
      catalog,
      db: database,
      table: tableName
    }
  })
}

onMounted(() => {
  getOptimizersList()
})
</script>
<style lang="less" scoped>
.optimizing-list-wrap {
  display: flex;
  width: 50%;
  flex-direction: column;
  padding: 0 24px 16px;
  box-shadow: 0 1px 4px rgb(0 21 41 / 8%);
  .primary-link {
    color: @primary-color;
    &:hover {
      cursor: pointer;
    }
  }
  .status-icon {
    width: 8px;
    height: 8px;
    border-radius: 8px;
    background-color: #c9cdd4;
    display: inline-block;
    margin-right: 8px;
  }
  :deep(.ant-table-wrapper) {
    height: 357px;
    .ant-spin-nested-loading,
    .ant-spin-container,
    .ant-table {
      height: 307px;
    }
    .ant-table-pagination.ant-pagination {
      margin-bottom: 10px;
    }
  }
}
</style>
