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

public class TOperationLogTable extends Table {

		public static final TOperationLogTable T_OPERATION_LOG = new TOperationLogTable();
		public final Column OPERATOR_LOG_ID = new Column(this, "operator_log_id");
		public final Column OPERATION_CONTENT = new Column(this, "operation_content");
		public final Column USER_ID = new Column(this, "user_id");
		public final Column OPERATION_IP = new Column(this, "operation_ip");
		public final Column OPERATION_TYPE = new Column(this, "operation_type");
		public final Column CREATE_TIME = new Column(this, "create_time");

		private TOperationLogTable() {
			super("t_operation_log");
		}

}
