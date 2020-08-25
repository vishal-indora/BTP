import random
import datetime

"""
Notes: 
**Use 24 Hour Clock for the code to work**
Don't use f-strings (not compatible with python version < 3.6)
"""

"""
PRINTED VALUES CORRESPONDING TO THE VARIABLES 

(Updated every second):
Time Elapsed -> time_elapsed
Time Remaining -> Time Remaining

(Updated every 15 minutes):
DC -> dc
SG -> sg
AG -> ag
Frequency -> frequency
Current Block Number -> current_block_number
Current Block Time -> current_block
Next Block Time -> next_block

Values under #Charges -> same as the jupyter file

Next Block's sg -> sg_plus_one
Next to Next block's sg -> sg_plus_two
and so on... -> sg_plus_three, sg_plus_four

(Updated every day)
Fuel Price -> fuel_price
"""


# Takes digit in int form and converts it to two digits (if only one) by adding preceding zero, returns str
def digit_convert(number: int) -> str:
    if len(str(number)) == 1:
        return '0' + str(number)
    else:
        return str(number)


# Takes a block number and returns a number between 1 and 96 i.e. converts block 97 to block 1, etc.
def block_number(n):
    if n > 96:
        return n % 96
    else:
        return n


def rupees_calculate():
    global frequency

    if 49.48 <= frequency <= 50.00:
        return 3.0304
    elif frequency == 50.01:
        return 2.5063
    elif frequency == 50.02:
        return 1.8798
    elif frequency == 50.03:
        return 1.2532
    elif frequency == 50.04:
        return 0.6266
    elif frequency == 50.05:
        return 0.00


def ui_dev_charge_calculate():
    global deviation, frequency, rupees

    if deviation <= 0 and frequency >= 49.85:
        return round(rupees * deviation * frequency, 2)


def ui_dev_charge_above_and_150_calculate():
    global frequency, sg, ag, deviation, rupees

    if frequency >= 49.85 and 0.12 * sg <= 150:
        if 0.85 * sg <= aa < 0.88 * sg:
            return round(-1 * 50 * (-deviation - 0.12 * sg), 2)
        elif 0.8 * sg <= ag < 0.85 * sg:
            return round((-1 * (100 * (-deviation - 0.15 * sg) + 1.5 * sg)) * rupees, 2)
        else:
            return round((-1 * (250 * (-deviation - 0.2 * s) + 6.5 * s)) * rupees, 2)
    else:
        return 0


def ui_dev_charge_above_and_012_calculate():
    global frequency, deviation, rupees, sg
    if frequency >= 49.85 and 0.12 * sg > 150:
        if 150 < deviation <= 200:
            return round((- 1 * (50 * (- deviation - 150)) * rupees), 2)
        elif 200 < -deviation <= 250:
            return round((- 1 * (100 * (-deviation - 200) + 2500) * rupees), 2)
        elif -deviation > 250:
            return round((- 1 * (250 * (- deviation - 250) + 7500)) * rupees, 2)
        else:
            return 0
    else:
        return 0


def ui_dev_charge_below_dc_calculate():
    global ag, sg, frequency, rupees, deviation

    if ag <= sg and frequency < 49.85:
        return round(deviation * rupees * 250, 2)
    else:
        return 0


def ui_dev_charge_below_dc_add_calculate():
    global ag, sg, frequency, deviation, rupees

    if ag <= sg and frequency < 49.85:
        return round(deviation * rupees * 250, 2)
    else:
        return 0


def oi_dev_charge_calculate():
    global sg, ag, deviation, rupees

    if ag > sg and deviation > 0.12 * sg and 0.12 * sg < 150:
        return round(0.12 * sg * rupees * 250, 2)
    elif ag > sg and deviation < sg * 0.12 and deviation < 150:
        return round(deviation * rupees * 250, 2)
    elif ag > sg and 0.12 * sg > 150:
        return round(150 * rupees * 250, 2)
    elif ag > sg and 0.12 * sg < 150:
        return round(0.12 * sg * rupees * 250, 2)


def oi_dev_charge_add_calculate():
    global ag, sg, frequency, deviation

    if ag > sg > 400 and frequency >= 50.1:
        return round(deviation * 250 * min(50.03, 0) / 100, 2)
    elif ag > sg and sg <= 400 and frequency >= 50.1 and deviation > 48:
        return round((deviation - 48) * 250 * min(50.03, 0) / 100, 2)


"""Read time from the system clock, using that, Assign current block number, current block and next block """
t = str(datetime.datetime.now().time())
t_hh, t_mm, t_ss = t[:2], t[3:5], t[6:8]
current_block_place = int(t_mm) // 15 + 1
next_block_place = 1 if current_block_place == 4 else current_block_place + 1

current_block_number = (int(t_hh) * 60 + int(t_mm)) // 15 + 1
current_block_start = t_hh + ":" + digit_convert((int(t_mm) // 15) * 15)
next_block_start = digit_convert(((int(t_hh) + 1) % 24) if current_block_place == 4 else t_hh) + ":" + function(t_mm)
current_block_end = next_block_start
next_block_end = digit_convert((int(next_block_start[:2]) + 1) % 24) + ":" + "00" if next_block_place == 4 else digit_convert(int(next_block_start[:2])) + ":" + digit_convert(int(next_block_start[-2:]) + 15)

current_block = current_block_start + "-" + current_block_end
next_block = next_block_start + "-" + next_block_end

"""From the time (used above) find the time elapsed and the time remaining"""
time_elapsed_mm, time_elapsed_ss = digit_convert(int(t_mm) % 15), digit_convert(int(t_ss))
time_remaining_in_seconds = digit_convert(15 * 60 - (int(time_elapsed_mm) * 60 + int(time_elapsed_ss)))
time_remaining_mm, time_remaining_ss = digit_convert(int(time_remaining_in_seconds) // 60), digit_convert(int(time_remaining_in_seconds) % 60)

time_elapsed = time_elapsed_mm + ":" + time_elapsed_ss
time_remaining = time_remaining_mm + ":" + time_remaining_ss


"""Randomizing the values as per the specified ranges"""

random.seed(current_block_number)
# Update in intervals of 15 minutes (1 time block)
dc = round(150 + random.random() * 50, 2)
sg = round(150 + random.random() * 50, 2)
ag = round(150 + random.random() * 50, 2)
frequency = round(49.5 + random.random() * 1.5, 2)
deviation = ag - sg

rupees = rupees_calculate()

# Charges
ui_dev_charge = ui_dev_charge_calculate()
ui_dev_charge_above_and_150 = ui_dev_charge_above_and_150_calculate()
ui_dev_charge_above_and_012 = ui_dev_charge_above_and_012_calculate()
ui_dev_charge_below_dc = ui_dev_charge_below_dc_calculate()
ui_dev_charge_below_dc_add = ui_dev_charge_below_dc_add_calculate()
oi_dev_charge = oi_dev_charge_calculate()
oi_dev_charge_add = oi_dev_charge_add_calculate()

sum_ui = ui_dev_charge + ui_dev_charge_above_and_150 + ui_dev_charge_above_and_012 + ui_dev_charge_below_dc + ui_dev_charge_below_dc_add
total_charge = sum_ui + oi_dev_charge + oi_dev_charge_add
total_charge_add = ui_dev_charge_above_and_150 + ui_dev_charge_above_and_012 + ui_dev_charge_below_dc_add + oi_dev_charge_add

fuel = deviation * 250 * 151.4 / 100 * (-1)
net_gain = fuel + total_charge


# SG for the next four blocks
random.seed(block_number(current_block_number + 1))
sg_plus_one = round(150 + random.random() * 50, 2)

random.seed(block_number(current_block_number + 2))
sg_plus_two = round(150 + random.random() * 50, 2)

random.seed(block_number(current_block_number + 3))
sg_plus_three = round(150 + random.random() * 50, 2)

random.seed(block_number(current_block_number + 4))
sg_plus_four = round(150 + random.random() * 50, 2)

# Updated every 24 hours
random.seed(int((str(datetime.date.today())[-2:])))
fuel_price = round(2 + random.random() * 8, 2)
