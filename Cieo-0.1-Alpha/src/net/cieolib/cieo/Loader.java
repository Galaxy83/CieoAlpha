package net.cieolib.cieo;

import java.util.List;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/*
 * Copyright 2013 Novikov Igor
 *
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
*@hide
*/
class Loader {
	protected static void loadMagazine(Context context, List<TextView> bullets,
			String previous, String current, int[] start_point) {
		if (previous.compareTo(current) == 0)
			return;
		if (current.isEmpty()) {
			bullets.clear();
			return;
		}
		int[] _start_point = { 0, 0 };
		if (start_point != null)
			_start_point = start_point;
		TextView tv;
		int prev_size = previous.length();
		int curr_size = current.length();
		int offset;
		int start_index;
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		if (prev_size <= curr_size) {
			if (prev_size == curr_size) {
				bullets.clear();
				start_index = 0;
				offset = prev_size;
			} else {
				offset = curr_size - prev_size;
				start_index = startIndex(previous, current);
			}
			for (int i = start_index; i < start_index + offset; i++) {
				tv = new TextView(context);
				tv.setLayoutParams(params);
				tv.setText(current.charAt(i) + "");
				tv.setX(_start_point[0]);
				tv.setY(_start_point[1]);
				bullets.add(i, tv);
			}
		} else {
			if (bullets.size() == 0)
				return;
			offset = prev_size - curr_size;
			start_index = startIndex(current, previous);
			for (int i = start_index + offset; i > start_index; i--) {
				bullets.remove(i - 1);
			}
		}
	}

	// This method will find the index of the first changed charecter.
	private static int startIndex(String small, String large) {
		char[] small_c = small.toCharArray();
		char[] large_c = large.toCharArray();
		int i = 0;
		for (i = 0; i < small_c.length; i++) {
			if (small_c[i] != large_c[i])
				return i;
		}
		return i;
	}
}
