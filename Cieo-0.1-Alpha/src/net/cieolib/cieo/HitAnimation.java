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
 * This animation will occur on the 'bullet' itself.
 * 
 * @author <b>Novikov Igor</b>
 */
public class HitAnimation {
	/**
	 * No Hit Animation.
	 */
	static final public int NONE = 0;
	/**
	 * After the 'bullet' will hit, it will "shake" horizontally.</br>Predefined
	 * settings:</br> Number of pixels to move: 1 .</br>Duration (speed): 70
	 * .</br>Number of repeats: 0 .
	 */
	static final public int SHAKE_HORIZONTAL1 = 2100;
	/**
	 * Custom <code>HitAnimation</code> "shake" horizontally. Pass array of int
	 * for <code>HitAnimation</code> settings:</br>array[0] = Number of pixels
	 * to move.</br>array[1] = Duration (speed).</br>array[2] = Number of
	 * repeats.
	 */
	static final public int SHAKE_HORIZONTAL0_CUSTOM = 2110;
	/**
	 * Custom <code>HitAnimation</code> "shake" vertically. Pass array of int
	 * for <code>HitAnimation</code> settings:</br>array[0] = Number of pixels
	 * to move.</br>array[1] = Duration (speed).</br>array[2] = Number of
	 * repeats.
	 */
	static final public int SHAKE_VERTICAL0_CUSTOM = 2120;
	/**
	 * After the 'bullet' will hit, it will "shake" vertically.</br>Predefined
	 * settings:</br>Number of pixels to move: 1 .</br>Duration (speed): 70 .
	 * </br>Number of repeats: 0 .
	 */
	static final public int SHAKE_VERTICAL1 = 2130;
}