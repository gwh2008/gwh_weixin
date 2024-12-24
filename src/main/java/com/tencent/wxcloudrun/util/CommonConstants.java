

package com.tencent.wxcloudrun.util;

/**
 * 
 * @date 2024/10/29
 */
public interface CommonConstants {

	String AUTHORIZATION = "authorization";

	/**
	 * 服务端路径
	 */
	String SERVER_URL = "http://127.0.0.1";

	/**
	 * 登录用户名
	 */
	String USERID = "USER-ID";

	/**
	 * 默认存表名称
	 */
	String DEF_TABLE_NAME = "order_run_application";

	/**
	 * 默认实现
	 */
	String DEF_SERVICE_IMPL = "runApplicationServiceImpl";

	/**
	 * 下一办理人是否为角色
	 */
	String NEXT_USER_ROLE = "ROLE_";

	/**
	 * 是否开启
	 */
	String IS_ENABLED = "true";

	/**
	 * 菜单信息缓存
	 */
	String MENU_DETAILS = "menu_details";

	/**
	 * 用户信息缓存
	 */
	String USER_DETAILS = "user_details";

	/**
	 * 角色信息缓存
	 */
	String ROLE_DETAILS = "role_details";


	/**
	 * 删除
	 */
	String STATUS_DEL = "1";

	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单树根节点
	 */
	Long MENU_TREE_ROOT_ID = -1L;

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "pigx-ui";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "pigx";

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;

	/**
	 * 失败标记
	 */
	Integer FAIL = 1;

	/**
	 * 是
	 */
	String YES = "1";

	/**
	 * 否
	 */
	String NO = "0";

}
