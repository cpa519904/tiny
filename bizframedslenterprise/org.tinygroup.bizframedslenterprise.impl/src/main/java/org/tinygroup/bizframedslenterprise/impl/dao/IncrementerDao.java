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

import static org.tinygroup.bizframedslenterprise.impl.dao.IncrementerTable.*;
import static org.tinygroup.tinysqldsl.Select.and;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.Incrementer;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;

public class IncrementerDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public Incrementer insertIncrementer(Incrementer incrementer) {
		Insert insert = insertInto(INCREMENTER).values(
				INCREMENTER.SEQUENCE_ID.value(incrementer.getSequenceId()));
		dslSession.execute(insert);
		return incrementer;
	}

	public void updateIncrementer(Incrementer incrementer) {
		Update update = update(INCREMENTER).set(
).where(
				INCREMENTER.SEQUENCE_ID.eq(incrementer.getSequenceId()));
		dslSession.execute(update);
	}

	public void deleteIncrementer(String id) {
		Delete delete = delete(INCREMENTER).where(INCREMENTER.SEQUENCE_ID.eq(id));
		dslSession.execute(delete);
	}

	public Incrementer getIncrementerById(String id) {
		Select select = selectFrom(INCREMENTER).where(INCREMENTER.SEQUENCE_ID.eq(id));
		return dslSession.fetchOneResult(select, Incrementer.class);
	}

	public List<Incrementer> queryIncrementers(Incrementer incrementer) {
		Select select = selectFrom(INCREMENTER).where(
				and(
));
		return dslSession.fetchList(select, Incrementer.class);
	}

}
