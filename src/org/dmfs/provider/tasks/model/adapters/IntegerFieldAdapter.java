/*
 * Copyright (C) 2015 Marten Gajda <marten@dmfs.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.dmfs.provider.tasks.model.adapters;

import android.content.ContentValues;
import android.database.Cursor;


/**
 * Knows how to load and store an {@link Integer} from a {@link Cursor} or {@link ContentValues}.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <EntityType>
 *            The type of the entity the field belongs to.
 */
public final class IntegerFieldAdapter<EntityType> extends SimpleFieldAdapter<Integer, EntityType>
{

	/**
	 * The field name this adapter uses to store the values.
	 */
	private final String mFieldName;


	/**
	 * Constructor for a new {@link IntegerFieldAdapter}.
	 * 
	 * @param fieldName
	 *            The name of the field to use when loading or storing the value.
	 */
	public IntegerFieldAdapter(String fieldName)
	{
		if (fieldName == null)
		{
			throw new IllegalArgumentException("fieldName must not be null");
		}
		mFieldName = fieldName;
	}


	@Override
	String fieldName()
	{
		return mFieldName;
	}


	@Override
	public Integer getFrom(ContentValues values)
	{
		// return the value as Integer
		return values.getAsInteger(mFieldName);
	}


	@Override
	public Integer getFrom(Cursor cursor)
	{
		int columnIdx = cursor.getColumnIndex(mFieldName);
		if (columnIdx < 0)
		{
			throw new IllegalArgumentException("The column '" + mFieldName + "' is missing in cursor.");
		}
		return cursor.isNull(columnIdx) ? null : cursor.getInt(columnIdx);
	}


	@Override
	public void setIn(ContentValues values, Integer value)
	{
		if (value != null)
		{
			values.put(mFieldName, value);
		}
		else
		{
			values.putNull(mFieldName);
		}
	}

}
