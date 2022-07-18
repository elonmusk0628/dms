import request from '@/utils/request'

// 查询安全帽信息列表
export function listHelmet(query) {
  return request({
    url: '/robot/helmet/list',
    method: 'get',
    params: query
  })
}
