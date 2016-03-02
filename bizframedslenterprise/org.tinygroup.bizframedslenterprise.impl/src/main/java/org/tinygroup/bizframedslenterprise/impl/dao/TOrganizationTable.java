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

public class TOrganizationTable extends Table {

		public static final TOrganizationTable T_ORGANIZATION = new TOrganizationTable();
		public final Column ORGANIZATION_ID = new Column(this, "organization_id");
		public final Column ORGANIZATION_NAME = new Column(this, "organization_name");
		public final Column PARENT_ID = new Column(this, "parent_id");
		public final Column SORT_NUM = new Column(this, "sort_num");

		private TOrganizationTable() {
			super("t_organization");
		}

}
