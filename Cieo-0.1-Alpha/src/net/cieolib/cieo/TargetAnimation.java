package net.cieolib.cieo;

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
 * This animation will occur on the target <code>TextView</code> after last
 * animation.</br>It will occur aftar <b>last</b> bullet's
 * <code>MainAnimation</code> if there is no <code>HitAnimation</code>,
 * otherwise after <b>last</b> bullet's <code>HitAnimation</code>.
 * 
 * @author <b>Novikov Igor</b>
 */
public class TargetAnimation {
	/**
	 * No Animation (<b>currently not recommended - see documentation</b>).
	 */
	static final public int NONE = 0;
	/**
	 * Pass array of int for <code>TargetAnimation</code> settings:</br>array[0]
	 * = The speed of the "flash" effect.
	 */
	static final public int FLASH_CUSTOM = 3100;
	/**
	 * Predefined settings:</br>"Flash" speed - 200
	 */
	static final public int FLASH_FAST = 3110;
	/**
	 * Predefined settings:</br>"Flash" speed - 500
	 */
	static final public int FLASH_SLOW = 3120;
}