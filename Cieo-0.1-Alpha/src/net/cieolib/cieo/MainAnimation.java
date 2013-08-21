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
 * Main animation types.
 * 
 * @author <b>Novikov Igor</b>
 * 
 */
public class MainAnimation {
	/**
	 * Pass array of int for <code>MainAnimation</code> settings:</br>array[0] =
	 * Time betwin bullets, in miliseconds.</br>array[1] = Flight duration of
	 * each 'bullet'.
	 */
	static final public int WAVE0_CUSTOM = 1100;
	/**
	 * Predefined settings:</br>Time between 'bullets' - 200</br>Flight duration
	 * - 3000
	 */
	static final public int WAVE1_SUPER_SLOW = 1110;
	/**
	 * Predefined settings:</br>Time between 'bullets' - 150</br>Flight duration
	 * - 1750
	 */
	static final public int WAVE2_SLOW = 1120;
	/**
	 * Predefined settings:</br>Time between 'bullets' - 150</br>Flight duration
	 * - 1750
	 */
	static final public int WAVE3_MODERATE = 1130;
	/**
	 * Predefined settings:</br>Time between 'bullets' - 50</br>Flight duration
	 * - 500
	 */
	static final public int WAVE4_FAST = 1140;
	/**
	 * Predefined settings:</br>Time between 'bullets' - 25</br>Flight duration
	 * - 250
	 */
	static final public int WAVE5_SUPER_FAST = 1150;
	/**
	 * Pass array of int for <code>MainAnimation</code> settings:</br>array[0] =
	 * Flight duration of all the 'bullets'.</br>Note that SHOTGUN animation is
	 * the same as WAVE animation, except that the time between 'bullets' is 0 .
	 */
	static final public int SHOTGUN0_CUSTOM = 1200;
	/**
	 * Predefined settings:</br>Flight duration - 900
	 */
	static final public int SHOTGUN1_SLOW = 1210;
	/**
	 * Predefined settings:</br>Flight duration - 400
	 */
	static final public int SHOTGUN2_MODERATE = 1220;
	/**
	 * Predefined settings:</br>Flight duration - 150
	 */
	static final public int SHOTGUN3_FAST = 1230;
}