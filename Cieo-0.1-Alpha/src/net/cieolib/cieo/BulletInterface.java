package net.cieolib.cieo;

import android.text.SpannableString;

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
 * Interface to help you listen to certain useful events.
 * 
 * @author <b>Novikov Igor</b>
 */
public interface BulletInterface {
	/**
	 * Invokes when the first bullet hits the target.
	 */
	public void onFirstBulletHit();

	/**
	 * Invokes when any bullet hits the target (including first and last).
	 */
	public void onBulletHit();

	/**
	 * Invokes when the last bullet hits the target.
	 * 
	 * @param span
	 *            The string you pass to your <code>ListAdapter</code> for
	 *            updating the target <code>TextView</code>.</br>Don't forget to
	 *            remove the previous (last) one from the list.
	 */
	public void onLastBulletHit(SpannableString span);

	/**
	 * Invokes when the <code>HitAnimation</code> of the <b>last</b> bullet
	 * ends.
	 * 
	 * @param span
	 *            The string you pass to your <code>ListAdapter</code> for
	 *            updating the target <code>TextView</code>.</br>Don't forget to
	 *            remove the previous (last) one from the list.
	 */
	public void onHitAnimationEnd(SpannableString span);

	/**
	 * Invokes when the <code>TargetAnimation</code> ends.
	 * 
	 * @param span
	 *            The string you pass to your <code>ListAdapter</code> for
	 *            updating the target <code>TextView</code>.</br>Don't forget to
	 *            remove the previous (last) one from the list.
	 */
	public void onTargetAnimationEnd(SpannableString span);
}