package net.cieolib.cieo;

import java.util.List;
import android.os.AsyncTask;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
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
 * @hide
 */
class Gun extends AsyncTask<Void, Object, Void> {
	List<TextView> bullets;
	TextView[] bullets_array;
	TextView target_tv, temp_tv;
	ViewGroup view_group;
	SpannableString spaned_string;
	Layout layout;
	BulletListener bl;
	int[] target_tv_location, view_group_location, charecter_x, lines,
			span_offset, main_animation_settings, hit_animation_settings,
			target_animation_settings;
	int main_animation, hit_animation, target_animation, line_correction,
			line_top, magazine_size, first_view_index, text_count,
			bullet_count;
	float density_multiplier, text_size;
	String[] string_lines;
	String original_string;

	protected Gun(List<TextView> bullets, String original_string,
			TextView to_tv, float density_multiplier, BulletListener bl,
			int main_animation, int[] main_animation_settings,
			int hit_animation, int[] hit_animation_settings,
			int target_animation, int[] target_animation_settings) {
		this.bullets = bullets;
		this.target_tv = to_tv;
		this.original_string = original_string;
		this.density_multiplier = density_multiplier;
		this.main_animation = main_animation;
		this.main_animation_settings = main_animation_settings;
		this.hit_animation = hit_animation;
		this.hit_animation_settings = hit_animation_settings;
		this.target_animation = target_animation;
		this.target_animation_settings = target_animation_settings;
		this.bl = bl;
		Init();
	}

	private void Init() {
		spaned_string = new SpannableString(original_string);
		spaned_string.setSpan(new ForegroundColorSpan(0x00000000), 0,
				spaned_string.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
		text_size = target_tv.getTextSize() / density_multiplier;
		layout = target_tv.getLayout();
		if (bl == null)
			bl = new BulletListener();
		target_tv_location = new int[2];
		view_group_location = new int[2];
		view_group = (ViewGroup) target_tv.getRootView();
		target_tv.getLocationOnScreen(target_tv_location);
		view_group.getLocationOnScreen(view_group_location);
		magazine_size = bullets.size();
		bullets_array = new TextView[bullets.size()];
		text_count = 0;
		for (TextView tv : bullets) {
			bullets_array[text_count++] = tv;
		}
		span_offset = new int[] { 0, 0 };
		text_count = 0;
		bullet_count = 0;
		line_top = 0;
		string_lines = getStringArrayOfLines(original_string);
	}

	@Override
	protected void onPreExecute() {
		target_tv.setText(spaned_string);
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		switch (main_animation) {
		case MainAnimation.WAVE0_CUSTOM:
			waveAnimationBackground(main_animation_settings);
			break;
		case MainAnimation.WAVE1_SUPER_SLOW:
			waveAnimationBackground(new int[] { 200, 3000 });
			break;
		case MainAnimation.WAVE2_SLOW:
			waveAnimationBackground(new int[] { 150, 1750 });
			break;
		case MainAnimation.WAVE4_FAST:
			waveAnimationBackground(new int[] { 50, 500 });
			break;
		case MainAnimation.WAVE5_SUPER_FAST:
			waveAnimationBackground(new int[] { 25, 250 });
			break;
		case MainAnimation.SHOTGUN0_CUSTOM:
			waveAnimationBackground(new int[] { 0, main_animation_settings[0] });
			break;
		case MainAnimation.SHOTGUN1_SLOW:
			waveAnimationBackground(new int[] { 0, 900 });
			break;
		case MainAnimation.SHOTGUN2_MODERATE:
			waveAnimationBackground(new int[] { 0, 400 });
			break;
		case MainAnimation.SHOTGUN3_FAST:
			waveAnimationBackground(new int[] { 0, 150 });
			break;
		default:
			waveAnimationBackground(new int[] { 100, 1000 });
			break;
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Object... values) {
		waveAnimationProgress(values);
	}

	private String[] getStringArrayOfLines(String original) {
		int longestLine = 0;
		int line_count = layout.getLineCount();
		String[] text_lines = new String[line_count];
		for (int i = 0; i < line_count; i++) {
			text_lines[i] = original.substring(layout.getLineStart(i),
					layout.getLineVisibleEnd(i));
			if (longestLine < text_lines[i].length())
				longestLine = text_lines[i].length();
		}
		String str = original.replaceAll("\\s", "");
		int count = 0;
		charecter_x = new int[str.length()];
		for (int i = 0; i < original.length(); i++) {
			if (Character.isWhitespace(original.charAt(i))) {
				continue;
			}
			charecter_x[count++] = (int) (layout.getPrimaryHorizontal(i))
					+ target_tv_location[0];
		}
		return text_lines;
	}

	protected void setOriginalString(String original_string) {
		this.original_string = original_string;
		spaned_string = new SpannableString(original_string);
		spaned_string.setSpan(new ForegroundColorSpan(0x00000000), 0,
				spaned_string.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	}

	protected void setBulletListener(BulletListener bl) {
		this.bl = bl;
	}

	private void waveAnimationBackground(int[] settings) {
		if (settings == null)
			settings = new int[] { 100, 1000 };
		for (int i = 0; i < string_lines.length; i++) {
			if (i == 1)
				line_correction = layout.getLineBottom(0) * 2
						- layout.getLineBottom(1);
			line_top = layout.getLineTop(i) - line_correction;
			for (int j = 0; j < string_lines[i].length(); j++) {
				if (Character.isWhitespace(original_string.charAt(text_count))) {
					text_count++;
					if (!Character.isWhitespace(string_lines[i].charAt(j)))
						j--;
					continue;
				}
				temp_tv = bullets.get(bullet_count);
				temp_tv.setTextSize(text_size);
				temp_tv.setTypeface(target_tv.getTypeface());
				span_offset[0] = span_offset[1];
				span_offset[1] = ++text_count;
				spaned_string.setSpan(
						new ForegroundColorSpan(temp_tv.getCurrentTextColor()),
						span_offset[0], span_offset[1], 0);
				publishProgress(temp_tv, target_tv_location[1]
						- view_group_location[1] + line_top, bullet_count++,
						settings[1]);
				try {
					Thread.sleep(settings[0]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 0 - tv
	// 1 - y
	// 2 - ui_count
	// 3 - time
	private void waveAnimationProgress(final Object... params) {
		final TextView bullet = (TextView) params[0];
		final int bullet_id = (Integer) params[2];
		view_group.addView(bullet);
		bullet.animate()
				.x(charecter_x[bullet_id])
				.y((Integer) params[1])
				.setDuration((Integer) params[3])
				.setListener(
						new Finish(target_tv, bullet, bullet_id, view_group,
								bl, magazine_size, spaned_string,
								bullets_array, hit_animation,
								hit_animation_settings, target_animation,
								target_animation_settings, density_multiplier));
	}
}