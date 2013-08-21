package net.cieolib.cieo;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.AsyncTask;
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
 * This is the main class for setting up and executing your animation.
 * 
 * @author <b>Novikov Igor</b>
 * @version 0.1
 * @since 0.1
 */
public class Cieo {
	private Context context;
	private String text;
	private Gun gun;
	private float density;
	private int main_animation, hit_animation, target_animation;
	private int[] main_animation_settings, hit_animation_settings,
			target_animation_settings;

	private List<TextView> bullets;
	private BulletListener bullet_listener;

	/**
	 * This is the main constructor.
	 * 
	 * This constructor is <b>not</b> recommended to be used unless you have all
	 * the necessary parameters. Then you can just use it without reference:
	 * <code>new Cieo(<<parameters>>).FIRE();</code>
	 * 
	 * @param context
	 *            Activity Context.
	 * @param text
	 *            The text that will be animated. Each character is a 'bullet'.
	 * @param target_text_view
	 *            The 'target' <code>TextView</code>.
	 * @param shoot_from
	 *            The 'origin' of fire. This is the x and y parameters on the
	 *            screen.
	 * @param bullet_listener
	 *            This is the listener that will notify the various events of
	 *            the animation, such as <code>lastBulletHit()</code>.</br>You
	 *            can catch the animation events in 3 ways:</br><b>1</b>. Your
	 *            class can implement the <code>BulletInterface</code>. In that
	 *            case, you will have to implement all the listeners'
	 *            methods.</br><b>2</b>. You can create a class that will extend
	 *            the abstract class <code>BulletAdapter</code>. Then you can
	 *            override only the methods you require.</br><b>3</b>. Use
	 *            <code>BulletListener</code> class that is already extending
	 *            <code>BulletAdapter</code>.
	 * @param main_animation
	 *            Main animation type.</br>Use <code>MainAnimation</code> class.
	 *            Default: <code>MainAnimation.WAVE3_MODERATE</code>
	 * @param main_animation_settings
	 *            Pass <code>null</code> unless <code>MainAnimation</code> is
	 *            custom.
	 * @param hit_animation
	 *            The type of animation that will occur <b>on</b> the 'bullet'
	 *            after it reaches target.</br>Use <code>HitAnimation</code>
	 *            class.</br>Default: <code>HitAnimation.NONE</code>
	 * @param hit_animation_settings
	 *            Pass <code>null</code> unless <code>HitAnimation</code> is
	 *            custom.
	 * @param target_animation
	 *            The type of animation that will occur on the 'target' after
	 *            last 'bullet' animation (<code>MainAnimation</code> or
	 *            <code>HitAnimation</code> depending if
	 *            <code>HitAnimation</code> exists) ends.</br>Default:
	 *            <code>TargetAnimation.FLASH_FAST</code>.
	 * @param target_animation_settings
	 *            Pass <code>null</code> unless <code>TargetAnimation</code> is
	 *            custom.
	 */
	public Cieo(Context context, String text, TextView target_text_view,
			int[] shoot_from, BulletListener bullet_listener,
			int main_animation, int[] main_animation_settings,
			int hit_animation, int[] hit_animation_settings,
			int target_animation, int[] target_animation_settings) {
		bullets = new ArrayList<TextView>();
		Loader.loadMagazine(context, bullets, "", text.replaceAll("\\s", ""),
				shoot_from);
		gun = new Gun(bullets, text, target_text_view, context.getResources()
				.getDisplayMetrics().density, bullet_listener, main_animation,
				main_animation_settings, hit_animation, hit_animation_settings,
				target_animation, target_animation_settings);
	}

	/**
	 * The 'step-by-step' constructor.
	 * 
	 * After creating a <code>Cieo</code> instance with this constructor, you
	 * <b>must</b> call the next methods before execution:
	 * <code>loadMagazine()</code> and <b>setTargetAndArm()</b>
	 * 
	 * @param context
	 *            Activity Context.
	 */
	public Cieo(Context context) {
		bullets = new ArrayList<TextView>();
		this.context = context;
		this.text = "";
		density = context.getResources().getDisplayMetrics().density;
		target_animation = -1;
	}

	/**
	 * <p>
	 * This method will load the 'magazine' with the Characters 'bullets' from
	 * the provided text, and sets the starting position of all the
	 * bullets.</br>If you fiering an user inputed text, it is stronglly
	 * recomended to pass the string after each users' key stroke.
	 * </p>
	 * 
	 * @param text
	 *            The text to be 'fired'.
	 * @param shoot_from
	 *            Start coordinates. <code>null</code> will result in default
	 *            coordinates (0,0).</br>Alternatively, you can set the starting
	 *            position of each 'bullet' manually by calling the
	 *            <code>getBullts()</code> method.
	 */
	public void loadMagazine(String text, int[] shoot_from) {
		Loader.loadMagazine(context, bullets, this.text.replaceAll("\\s", ""),
				text.replaceAll("\\s", ""), shoot_from);
		this.text = text;
		if (gun != null)
			gun.setOriginalString(text);
	}

	/**
	 * The final method to be called before <code>FIRE()</code>.</br>If any
	 * other method is called after this one, you will have to re-arm (call this
	 * method again).
	 * 
	 * @param target_text_view
	 *            The 'target' TextView.
	 */
	public void setTargetAndArm(TextView target_text_view) {
		if (text == null)
			text = "";

		gun = new Gun(bullets, text, target_text_view, density,
				bullet_listener, main_animation, main_animation_settings,
				hit_animation, hit_animation_settings, target_animation,
				target_animation_settings);
	}

	/**
	 * This method is a must when your 'target' <code>TextView</code> is inside
	 * <code>ListView</code>.
	 * 
	 * @param bullet_listener
	 *            The listener that will notify the various events of the
	 *            animation, such as <code>lastBulletHit()</code>.
	 */
	public void setBulletListener(BulletListener bullet_listener) {
		this.bullet_listener = bullet_listener;
	}

	/**
	 * Gets the 'magazine' for manually changing settings such as color and
	 * start coordinates of each bullet.
	 * 
	 * @return List<TextView> of 'bullets'.
	 */
	public List<TextView> getBullets() {
		return bullets;
	}

	/**
	 * Animation settings.
	 * 
	 * @param main_animation
	 *            Main animation type.</br>Use <code>MainAnimation</code> class.
	 *            Default: <code>MainAnimation.WAVE3_MODERATE</code>
	 * @param main_animation_settings
	 *            Pass <code>null</code> unless <code>MainAnimation</code> is
	 *            custom.
	 * @param hit_animation
	 *            The type of animation that will occur <b>on</b> the 'bullet'
	 *            after it reaches target.</br>Use <code>HitAnimation</code>
	 *            class.</br>Default: <code>HitAnimation.NONE</code>
	 * @param hit_animation_settings
	 *            Pass <code>null</code> unless <code>HitAnimation</code> is
	 *            custom.
	 * @param target_animation
	 *            The type of animation that will occur on the 'target' after
	 *            last 'bullet' animation (<code>MainAnimation</code> or
	 *            <code>HitAnimation</code> depending if
	 *            <code>HitAnimation</code> exists) ends.</br>Default:
	 *            <code>TargetAnimation.FLASH_FAST</code>.
	 * @param target_animation_settings
	 *            Pass <code>null</code> unless <code>TargetAnimation</code> is
	 *            custom.
	 */
	public void setAnimation(int main_animation, int[] main_animation_settings,
			int hit_animation, int[] hit_animation_settings,
			int target_animation, int[] target_animation_settings) {
		this.main_animation = main_animation == 0 ? MainAnimation.WAVE3_MODERATE
				: main_animation;
		this.main_animation_settings = main_animation_settings;
		this.hit_animation = hit_animation;
		this.hit_animation_settings = hit_animation_settings;
		this.target_animation = target_animation;
		this.target_animation_settings = target_animation_settings;
	}

	/**
	 * Executes the animation.</br>Can be executed only <b>ONCE</b>.
	 */
	public void FIRE() {
		if (gun != null)
			gun.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
	}
}
