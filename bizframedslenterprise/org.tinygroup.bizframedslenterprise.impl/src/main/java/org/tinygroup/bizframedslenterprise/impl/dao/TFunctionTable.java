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

public class TFunctionTable extends Table {

		public static final TFunctionTable T_FUNCTION = new TFunctionTable();
		public final Column FUNCTION_ID = new Column(this, "function_id");
		public final Column PARENT_ID = new Column(this, "parent_id");
		public final Column FUNCTION_NAME = new Column(this, "function_name");
		public final Column FUNCTION_TYPE = new Column(this, "function_type");
		public final Column FUNCTION_URL = new Column(this, "function_url");
		public final Column SORT_NUM = new Column(this, "sort_num");
		public final Column FUNCTION_CODE = new Column(this, "function_code");

		private TFunctionTable() {
			super("t_function");
		}

}
