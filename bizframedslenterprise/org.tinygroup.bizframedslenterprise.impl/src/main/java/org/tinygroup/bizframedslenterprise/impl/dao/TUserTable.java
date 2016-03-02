/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.tinygroup.bizframedslenterprise.impl.dao;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class TUserTable extends Table {

		public static final TUserTable T_USER = new TUserTable();
		public final Column USER_ID = new Column(this, "user_id");
		public final Column ORGANIZATION_ID = new Column(this, "organization_id");
		public final Column USER_NAME = new Column(this, "user_name");
		public final Column USER_PASSWORD = new Column(this, "user_password");
		public final Column USER_TYPE = new Column(this, "user_type");
		public final Column USER_STATUS = new Column(this, "user_status");
		public final Column SORT_NUM = new Column(this, "sort_num");
		public final Column LOGIN_NAME = new Column(this, "login_name");
		public final Column IS_DEFAULT = new Column(this, "is_default");

		private TUserTable() {
			super("t_user");
		}

}
