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
 * Abstract class that implements <code>BulletInterface</code>.</br>Inherit this
 * class to override only neccessary methods.
 * 
 * @author <b>Novikov Igor</b>
 */
abstract public class BulletAdapter implements BulletInterface {
	@Override
	public void onFirstBulletHit() {
	}

	@Override
	public void onBulletHit() {
	}

	@Override
	public void onLastBulletHit(SpannableString span) {
	}

	@Override
	public void onHitAnimationEnd(SpannableString span) {
	}

	@Override
	public void onTargetAnimationEnd(SpannableString span) {
	}
}