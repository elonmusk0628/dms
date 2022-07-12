import request from '@/utils/request'

// 查询仪表信息列表
export function listDevice(query) {
  return request({
    url: '/device/monitor/list',
    method: 'get',
    params: query
  })
}